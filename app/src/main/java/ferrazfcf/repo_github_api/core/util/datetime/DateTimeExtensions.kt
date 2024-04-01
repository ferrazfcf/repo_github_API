package ferrazfcf.repo_github_api.core.util.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDate.format(pattern: String) = LocalDate.Format {
    byUnicodePattern(pattern)
}.format(this)
