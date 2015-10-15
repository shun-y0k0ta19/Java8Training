#!/usr/bin/jjs -scripting
function printNextAge(age) {
    print('Next year, you will be ' + (Number(age) + 1))
}

if(arguments.length == 1) {
    printNextAge(arguments[0])
} else if('AGE' in $ENV) {
    printNextAge($ENV.AGE)
} else {
    var scanner = new java.util.Scanner(java.lang.System.in)
    
    while(true) {
	print('Input your age: ')
	if(scanner.hasNextInt()) {
	    printNextAge(scanner.nextInt())
	    break;
	} else {
	    print('Invalid value. Please try again.')
	    scanner.next()
	}
    }
}
    
