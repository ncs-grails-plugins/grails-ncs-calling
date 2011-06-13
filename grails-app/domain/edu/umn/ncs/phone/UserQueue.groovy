package edu.umn.ncs.phone

import edu.umn.ncs.TrackedItem

// A user's personal queue of cases they are working on
class UserQueue {

	String username
	
	static hasMany = [ trackedItems : TrackedItem ]
	
    static constraints = {
		username(unique:true)
    }
}
