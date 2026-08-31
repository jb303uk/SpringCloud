@import org.springframework.data.domain.Window
@import uk.co.jb303.entity.CatchSummary
@import uk.co.jb303.service.SpriteService

@param uk.co.jb303.entity.Catches Catches
@param String USERUUID

@param Window<CatchSummary> allCatches
@param String sortAll
@param String dirAll
@param int sizeAll
@param Boolean hasNext
@param Long nextLastCatchId

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <meta name="color-scheme" content="light dark"> -->
    <title>🥚</title>
    <style>
    body { font-family: Arial, sans-serif; margin: 10px; background-color: #f4f4f9; }
    input { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
    button { width: 100%; padding: 1px; background-color: #005a9c; color: white; border: none; border-radius: 4px; font-size: 40px; cursor: pointer; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    th, td { border: 1px solid #cccccc; padding: 5px; }
    tr:nth-child(even) { background-color: #f9f9f9; }
    .pagination { display: flex; gap: 10px; list-style: none; }
    </style>
</head>
<body>
<div class="container">
    <form action="/" method="POST">
        <button type="submit">🥚</button>
        <input type="hidden" id="USERUUID" name="USERUUID" value="${USERUUID}"> 
    </form>
    </div>
    <table>
        <thead>
            <tr>
                <th><a href="?sortAll=isShiny&dirAll=${dirAll.equals("asc") ? "asc" : "desc"}">Icon</a></th>
                <th><a href="?sortAll=catchId&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">Count</a></th>
                <th><a href="?sortAll=speciesId&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">#</a></th>
                <th><a href="?sortAll=pokemonName&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">Name</a></th>
                <th><a href="?sortAll=pokemonGen&dirAll=${dirAll.equals("asc") ? "desc" : "asc"}">Gen</a></th>
                <th><a href="?sortAll=ivsPercent&dirAll=${dirAll.equals("asc") ? "asc" : "desc"}">IVs %</a></th>
            </tr>
        </thead>
        <tbody>
            @for(CatchSummary catchRecord : allCatches.getContent())
                <tr>
                    <td><img class="sprite" 
                         src="${SpriteService.getSpriteDataUri(catchRecord.getSpeciesId(), catchRecord.getIsShiny())}" 
                         alt="${catchRecord.getPokemonName()}" 
                         loading="lazy" />${catchRecord.getShinyIcon()}</td>
                    <td>${catchRecord.getCatchId()}</td>
                    <td>${catchRecord.getSpeciesId()}</td>
                    <td>${catchRecord.getPokemonName()}</td>
                    <td>${catchRecord.getPokemonGen()}</td>
                    <td>${catchRecord.getIVsPercent()}</td>
                </tr>
            @endfor
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
                <li>
                    <a href="?sizeAll=${sizeAll}&sortAll=${sortAll}&dirAll=${dirAll}">« Previous</a>
                </li>
                @if(hasNext && nextLastCatchId != null)
                <li>
                    <a href="?lastCatchId=${nextLastCatchId}&sizeAll=${sizeAll}&sortAll=${sortAll}&dirAll=${dirAll}">Next »</a>
                </li>
                @endif
        </ul>
    </nav>
</body>
</html>
