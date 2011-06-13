package edu.umn.ncs.phone

class CallResultCategory {
	
	String name
	
	Boolean success = true
	Boolean problem = false
	
	String toString() { name }
	
    static constraints = {
		name()
		success()
		problem()
    }
}
