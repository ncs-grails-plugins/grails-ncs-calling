package edu.umn.ncs.phone

import edu.umn.ncs.TrackedItem
import edu.umn.ncs.Result
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

// the final call system result for an item
class ItemCallResult {
	
	static auditable = true
	
	TrackedItem trackedItem
	Result associatedResult
	Date dateClosed = new Date()
	
	Date dateCreated = new Date()
	Date lastUpdated
	String userCreated
	String userUpdated

	static hasMany = [ nonParticipationReasons: NonParticipationReason ]

	// for auditing...
	def onDelete = { oldMap ->
		def now = new Date()
		
		String oldValue = """ItemCallResult for Item:${oldMap.trackedItem?.id}, 
Result:${oldMap.associatedResult.id} on ${oldMap.dateClosed}. 
Created: [on: ${oldMap.dateCreated} by:${oldMap.userCreated}] 
Updated: [on: ${oldMap.lastUpdated} by: ${oldMap.userUpdated}]"""
		
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
	
	// Final Result for a call
    static constraints = {
		trackedItem(unique:true)
		associatedResult()
		dateClosed()
		dateCreated()
		lastUpdated(nullable:true)
		userCreated()
		userUpdated(nullable:true)
    }
}
