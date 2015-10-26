#!/usr/bin/jjs -scripting

function bufferedReader(input) {
    return new java.io.BufferedReader(new java.io.InputStreamReader(input))
}

function bufferedWriter(output) {
    return new java.io.BufferedWriter(new java.io.OutputStreamWriter(output))
}

function pipe() {
    var processes = [];
    for(var i = 0; i < arguments.length; i++) {
	var pb = new java.lang.ProcessBuilder(arguments[i].split(' '))
	processes[i] = pb.start()
	if(i != 0) {
	    var input = bufferedReader(processes[i-1].getInputStream())
	    var output = bufferedWriter(processes[i].getOutputStream())
	    new java.lang.Thread(function() {
		var data
		while ((data = input.read()) != -1) {
		    print(data)
		    output.write(data)
		}
		output.close()
	    }).start()
	}
    }
    print('scan')
    print(processes.length)
    var scanner = new java.util.Scanner(processes[2].getInputStream())
    var output = ""
    while (scanner.hasNextLine()) {
	output += scanner.nextLine() + "\n"
	print(output)
    }
    return output
}

//print(pipe('ls -l'))
print(pipe('ls', 'grep js', 'sort'))

