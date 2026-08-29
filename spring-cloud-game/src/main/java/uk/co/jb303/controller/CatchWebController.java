package uk.co.jb303.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import uk.co.jb303.entity.Catches;
import uk.co.jb303.repository.CatchRepository;
import uk.co.jb303.repository.CatchView;

@Controller
@RequestMapping("/")
public class CatchWebController {

    private final CatchRepository catchRepository;
    private final CatchView catchView;

    public CatchWebController(CatchRepository catchRepository, CatchView catchView) {
        this.catchRepository = catchRepository;
        this.catchView = catchView;
    }

    @GetMapping("/")
    public String getDashboard(
            @RequestParam(defaultValue = "5") int sizeAll,
            @RequestParam(defaultValue = "catchId") String sortAll,
            @RequestParam(defaultValue = "desc") String dirAll,
            @RequestParam(required = false) Long lastCatchId,
            @CookieValue(name = "userUUID", required = false) String userUUID,
            HttpServletResponse response,
            Model model) {

        String uuid = userUUID;
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("userUUID", uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        model.addAttribute("USERUUID", uuid);

        // 1. Configure sorting (Always ensure sorting includes a unique key like catchId)
        Sort.Direction direction = dirAll.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortAll);

        // 2. Build KeysetScrollPosition
        ScrollPosition position;
        if (lastCatchId != null) {
            // Resume from last seen cursor/key
            position = ScrollPosition.forward(Map.of("catchId", lastCatchId));
        } else {
            // First page / initial scroll
            position = ScrollPosition.keyset();
        }

        // 3. Fetch Window
        Window<?> catchWindow = catchView.findByUserUUID(uuid, position, Limit.of(sizeAll), sort);

        // 4. Extract cursor for next page if more results exist
        Long nextLastCatchId = null;
        if (catchWindow.hasNext() && !catchWindow.isEmpty()) {
            ScrollPosition nextPosition = catchWindow.positionAt(catchWindow.size() - 1);
            if (nextPosition instanceof KeysetScrollPosition keyset) {
                Object keyVal = keyset.getKeys().get("catchId");
                if (keyVal instanceof Number number) {
                    nextLastCatchId = number.longValue();
                }
            }
        }

        model.addAttribute("allCatches", catchWindow);
        model.addAttribute("hasNext", catchWindow.hasNext());
        model.addAttribute("nextLastCatchId", nextLastCatchId);
        model.addAttribute("sortAll", sortAll);
        model.addAttribute("dirAll", dirAll);
        model.addAttribute("sizeAll", sizeAll);

        return "catches";
    }

    @PostMapping("/")
    public String insertCatches(@ModelAttribute Catches catches) {
        catchRepository.save(catches);
        return "redirect:/";
    }
}