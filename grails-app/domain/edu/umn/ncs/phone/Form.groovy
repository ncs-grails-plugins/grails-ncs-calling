package edu.umn.ncs.phone

import edu.umn.ncs.Instrument

class Form {
	
	String name
	Instrument linkedInstrument
	
	String toString() { name }
	
    static constraints = {
		name()
		linkedInstrument(nullable:true)
    }
}
