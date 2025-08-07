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

requirement3:
 - I changed my mind, I implemented set method as package-private method and delegate correct status setting to repository

requirement4:
 - I am going to implement mission handling similarly to rockets, i will store missions in the list in the repository class

requirement5:
 - I will create separate view of relation between rockets and missions as additional hashmap that will store Missions as keys and list of rockets assigned to it
 - New list will be updated accordingly in 'assignRocketToMission' method of repository class

requirement6:
 - just handling status for different cases in 'assignRocketToMission' method

requirement7:
 - I havent started with name for Mission and Rocket implementation as i wanted to be straight forward, but now i have added handling for names too
 - For implementation of summary method I think some refactor will be required. My approach is to store number of assigned rockets directly in Mission class and then use treemap to store missions and list of rockets in order by mission key
 - i first start with implementing count storage in mission class
 - Then implement comparable interface on Mission class
 - i implemented compareTo method to sort in descending order to prevent calling reverse method. It is more optimal

