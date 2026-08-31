package uk.co.jb303.controller;

import gg.jte.TemplateEngine;
import gg.jte.output.Utf8ByteOutput;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.jb303.entity.Catches;
import uk.co.jb303.repository.CatchRepository;
import uk.co.jb303.repository.CatchView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class CatchWebController {

    private final CatchRepository catchRepository;
    private final CatchView catchView;
    private final TemplateEngine templateEngine;

    public CatchWebController(CatchRepository catchRepository, CatchView catchView, TemplateEngine templateEngine) {
        this.catchRepository = catchRepository;
        this.catchView = catchView;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/")
    public void getDashboard(
            @RequestParam(defaultValue = "5") int sizeAll,
            @RequestParam(defaultValue = "catchId") String sortAll,
            @RequestParam(defaultValue = "desc") String dirAll,
            @RequestParam(required = false) Long lastCatchId,
            @CookieValue(name = "userUUID", required = false) String userUUID,
            HttpServletResponse response) throws IOException {

        String uuid = userUUID;
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("userUUID", uuid);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        // 1. Configure sorting
        Sort.Direction direction = dirAll.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortAll);

        // 2. Build KeysetScrollPosition
        ScrollPosition position;
        if (lastCatchId != null) {
            position = ScrollPosition.forward(Map.of("catchId", lastCatchId));
        } else {
            position = ScrollPosition.keyset();
        }

        // 3. Fetch Window
        Window<?> catchWindow = catchView.findByUserUUID(uuid, position, Limit.of(sizeAll), sort);

        // 4. Extract cursor for next page
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

        // 5. Prepare template data map (or pass a dedicated Page DTO)
        Map<String, Object> modelParams = new HashMap<>();
        modelParams.put("USERUUID", uuid);
        modelParams.put("allCatches", catchWindow);
        modelParams.put("hasNext", catchWindow.hasNext());
        modelParams.put("nextLastCatchId", nextLastCatchId);
        modelParams.put("sortAll", sortAll);
        modelParams.put("dirAll", dirAll);
        modelParams.put("sizeAll", sizeAll);

        // 6. Zero-copy binary output rendering via JTE Utf8ByteOutput
        Utf8ByteOutput output = new Utf8ByteOutput();
        templateEngine.render("catches.jte", modelParams, output);

        response.setContentType("text/html;charset=UTF-8");
        response.setContentLength(output.getContentLength());
        
        try (OutputStream os = response.getOutputStream()) {
            output.writeTo(os);
        }
    }

    @PostMapping("/")
    public String insertCatches(@ModelAttribute Catches catches) {
        catchRepository.save(catches);
        return "redirect:/";
    }
}