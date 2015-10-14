#!/usr/bin/jjs -scripting

function pipe() {
	for each(var arg in arguments) { 
		var output = $EXEC(arg, output)
	}
	return output
}

print(pipe('ls', 'grep e', 'sort'))

