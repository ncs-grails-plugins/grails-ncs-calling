package edu.umn.ncs.phone

class NonParticipationReason {

	String name
		
	String toString() { name }
    static constraints = {
		name()
    }
}
