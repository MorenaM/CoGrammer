
def replace(string, subString, replacer):	 
	
    # Find the index of the substring
    index = string.find(subString)
    
    # Base case if string does not have the substring
	# return the string
    if index == -1:
        return string
         
    # If not then we have to call 
    # recursion from after the index + length of substring
    # then add recursion result to the elements before substring
    # and add the replacer and return it
    smallOutput = replace(string[index+len(subString):], subString, replacer)
    return string[:index] + replacer + smallOutput
       
# Driver code
if __name__ == "__main__":

    string = input("Please enter a string: ") 
    subString = input("Please enter the substring you wish to find: ") 
    replacer = input("Please enter a string to replace the given substring: ") 

    # Function call
    newString = replace(string, subString, replacer)
    print("\nYour new string is: " + newString)
