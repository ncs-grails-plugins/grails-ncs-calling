package edu.umn.ncs.phone

class AdminTask {
	String name
	
	String toString() { name }
	
    static constraints = {
		name()
    }
}
