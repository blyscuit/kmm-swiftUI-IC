apply {
    plugin("io.gitlab.arturbosch.detekt")
}

detekt {
    toolVersion = "1.0.0"
    input = files("src")
    filters = ".*/resources/.*,.*/build/.*"
    config = files(file("$project.rootDir/tools/detekt.yml"))
    reports {
        xml {
            enabled = true
            destination = file("build/reports/detekt/detekt-result.xml")
        }
        html {
            enabled = true
            destination = file("build/reports/detekt/detekt-result.html")
        }
    }
}