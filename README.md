requirement 1:
 - first I want to start with an implementation of simple rocker object that later we will store in the repository
 - I also create enum for storing status of the rocket
 - I dont override equals method for rocket as I think it will make more sense to have rockets as separate entities
 - I simply implemented adding rockets to list and implemented access methods with package-private access for testing in the repository
 - Im adding another constructor for rocket class with package-private scope to test adding to repository case
