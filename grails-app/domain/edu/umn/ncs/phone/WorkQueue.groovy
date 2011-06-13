package edu.umn.ncs.phone

import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent
import edu.umn.ncs.TrackedItem

// this is a queue to track items that need to be acted/called upon
// similar to the physical bins to process work queues
class WorkQueue {
	
	static auditable = true
	
	String name
	Integer priority = 1
	
	String toString() { name }
	
	static hasMany = [ trackedItems : TrackedItem ]
	
    static constraints = {
		name()
		priority(range:1..10)
    }
}
