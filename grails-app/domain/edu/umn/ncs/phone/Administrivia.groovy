package edu.umn.ncs.phone

import edu.umn.ncs.TrackedItem
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

/** This class is used to track administrative time associated with a tracked item */
class Administrivia {
	
	static auditable = true
	// call time/date information
	Date startTime = new Date()
	Date endTime
	// username of the person who made/took the call
	String phoningUser
	
	// data entry stamps
	Date dateCreated = new Date()
	Date lastUpdated
	String userCreated
	String userUpdated
	
	String comments
	
	static hasMany = [ items : TrackedItem
		, formsCompleted: Form
		, tasks: AdminTask ]

	def onDelete = { oldMap ->
		def now = new Date()
		
		def oldItemIds = oldMap.items?.collect{ it.id }.join(',')
		def formIds = oldMap.formsCompleted?.collect{ it.id }.join(',')
		def taskIds = oldMap.tasks?.collect{ it.id }.join(',')
		
		String oldValue = "Administrivia for Items: ${oldItemIds}"
		oldValue += ", startTime: ${oldMap.startTime}"
		oldValue += ", endTime: ${oldMap.endTime}"
		oldValue += ", phoningUser: ${oldMap.phoningUser}"
		
		oldValue += ", forms: ${oldMap.formIds}"
		oldValue += ", tasks: ${oldMap.taskIds}"
		oldValue += ", comments: \"${oldMap.comments}\""
		
		
		oldValue += ". Created: [on: ${oldMap.dateCreated} by:${oldMap.userCreated}] "
		oldValue += "Updated: [on: ${oldMap.lastUpdated} by: ${oldMap.userUpdated}]"
		
		String className = this.class.toString().replace('class ', '')
		
		def auditLogEventInstance = new AuditLogEvent(className: className,
				dateCreated: now,
				eventName: 'DELETE',
				lastUpdated: now,
				oldValue: oldValue,
				persistedObjectId: this.id,
				persistedObjectVersion: this.version)
		
		if ( ! auditLogEventInstance.save() ) {
			auditLogEventInstance.errors.each{
				println "${now}\tError Transacting DELETE:: \t ${it}"
			}
		}
	}
	
    static constraints = {
		startTime()
		endTime(nullable:true)
		phoningUser()
		dateCreated()
		lastUpdated(nullable:true)
		userCreated()
		userUpdated(nullable:true)	
		comments(nullable:true)
    }
}
