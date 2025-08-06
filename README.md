requirement 1:
 - first I want to start with an implementation of simple rocker object that later we will store in the repository
 - I also create enum for storing status of the rocket
 - I dont override equals method for rocket as I think it will make more sense to have rockets as separate entities
 - I simply implemented adding rockets to list and implemented access methods with package-private access for testing in the repository
 - Im adding another constructor for rocket class with package-private scope to test adding to repository case

requirement 2:
 - i decided to implement relation of rocket-mission as a simple hashmap to decouple mission handling from rocket object itself as a repository should be storing missions
 - i go with HashMap implementation, but for thread safety we could use ConcurrentHashMap (this would require some refactoring to use methods like putIfAbsent/computeIfAbsent, than sole put(),get())
 - I added Mission class for future implementation
 - I dont bother implementing equals method as Missions might be separate entities
 - I implement additional check for rocket's existence in the storage list as I think it makes more sense to assign missions to rockets that the repository has.