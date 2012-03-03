grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// Plugin Publishing Repository Information
grails.project.repos.umnNcs.url = "http://artifact.ncs.umn.edu/plugins-release-local"
grails.project.repos.umnNcs.type = "maven"
grails.project.repos.default = "umnNcs"
grails.release.scm.enabled = false

// Dependency Resolution
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

		mavenRepo "http://artifact.ncs.umn.edu/plugins-release"
    }
    dependencies {
        // runtime 'mysql:mysql-connector-java:5.1.10'
    }
}
codenarc.reports = {
	JenkinsXmlReport('xml') {
		outputFile = 'target/test-reports/CodeNarcReport.xml'
		title = 'CodeNarc Report for NCS Calling Plugin'
	}
	JenkinsHtmlReport('html') {
		outputFile = 'CodeNarcReport.html'
		title = 'CodeNarc Report for NCS Calling Plugin'
	}
}
codenarc.propertiesFile = 'grails-app/conf/codenarc.properties'
