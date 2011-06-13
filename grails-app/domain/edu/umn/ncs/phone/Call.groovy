package edu.umn.ncs.phone

import edu.umn.ncs.TrackedItem
import org.joda.time.LocalDateTime
import org.codehaus.groovy.grails.plugins.orm.auditable.AuditLogEvent

// used to track phone calls associated with a particular item
class Call {
	
	static auditable = true
	
	// call time/date information
	Date startTime = new Date()
	Date endTime
	// username of the person who made/took the call
	String phoningUser
	// number dialed
	String numberDialed
	// if someone other than the intended party was contacted
	String alternateContactedParty
	// result of the call
	CallResult callResult

	// Call Direction
	Boolean outgoing = true
	
	String comments
	
	// data entry stamps
	Date dateCreated = new Date()
	Date lastUpdated
	String userCreated
	String userUpdated
	
	static hasMany = [ items : TrackedItem ]
	static transients = [ 'hourOfDay', 'dayOfWeek']
	
	// implement some JodaTime methods
	Integer getHourOfDay() {
		def jodaCallTime = new LocalDateTime(startTime)
		return jodaCallTime.hourOfDay
	}

	Integer getDayOfWeek() {
		def jodaCallTime = new LocalDateTime(startTime)
		return jodaCallTime.dayOfWeek
	}

	def onDelete = { oldMap ->
		def now = new Date()
		
		def oldItemIds = oldMap.items?.collect{ it.id }.join(',')
		
		String oldValue = "Call for Items: ${oldItemIds}"
		oldValue += ", startTime: ${oldMap.startTime}"
		oldValue += ", endTime: ${oldMap.endTime}"
		oldValue += ", phoningUser: ${oldMap.phoningUser}"
		
		oldValue += ", alternateContactedParty: ${oldMap.alternateContactedParty}"
		oldValue += ", callResult: ${oldMap.callResult?.id}"
		oldValue += ", outgoing: ${oldMap.outgoing}"
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
		numberDialed(nullable:true)
		alternateContactedParty(nullable:true)
		callResult(nullable:true)
		outgoing()
		comments(nullable:true)
		dateCreated()
		lastUpdated(nullable:true)
		userCreated()
		userUpdated(nullable:true)
    }
	static mapping = {
		table 'phone_call'
	}
}
