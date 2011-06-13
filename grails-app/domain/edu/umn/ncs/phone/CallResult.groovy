package edu.umn.ncs.phone

// the result of a call
class CallResult {
	String name
	String abbreviation
	String description
	
	CallResultCategory callResultCategory
	
	String toString() { name }
	
    static constraints = {
		name()
		abbreviation(maxSize: 16)
		description(maxSize: 1024)
		callResultCategory()
    }
}
