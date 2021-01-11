
# Driver code
if __name__ == "__main__":

    string = input("Please enter a string: ") 
    subString = input("Please enter the substring you wish to find: ") 
    replacer = input("Please enter a string to replace the given substring: ") 

    # Function call
    newString = replace(string, subString, replacer)
    print("Your new string is: " + newString)
