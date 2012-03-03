/*
    NCS Calling Schema Plugin for the National Children's Study
    Copyright (C) 2011, Regent's of the University of Minnesota

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
class NcsCallingGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [ ncsTracking:"0.5 > *", jodaTime: "1.0 > *"]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/conf/*",
			"lib/"
    ]

    def author = "Aaron J. Zirbes"
    def authorEmail = "ajz@umn.edu"
    def title = "Provides Schema to support call logging"
    def description = '''\\
This plugin provides domain classes to support call logging within the NCS case management system.
The use of these domain classes through a plugin allows them to be used in other applications without
having to fork the code.
'''

    // URL to the plugin's documentation
    def documentation = "https://www.ncs.umn.edu/ncs-grails-plugin/ncs-calling"

}
