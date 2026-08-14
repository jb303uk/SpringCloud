@import java.util.List
@import uk.co.jb303.entity.LinkItem

@param String pageTitle
@param List<LinkItem> links

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
</head>
<body>
    <h1>${pageTitle}</h1>
    <ul>
        @for(LinkItem link : links)
            <li>
                <a href="${link.url()}" target="_blank" rel="noopener">${link.name()}</a>
            </li>
        @endfor
    </ul>
</body>
</html>
