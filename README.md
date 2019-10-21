# Spotify2
Music App

Mon: Step 1 and 2: setup spring and simple hello test

- What we did: set up spring config, UserController(API RestContoller with RequestMapping/GetMapping), spring profile
- Design Decision: no much design
- How we did it: pair programming
- Challenges: Unfamiliar with URL
- What we like: refresh the knowledge

Tue-Sun: step 3: Hibernate

- What we did: designed necessary interface and built up entity-dao-service-controller layers for app
- Design Decision:
- - we have 4 entities: user, song, userprofile, userrole
- - - user needs to: signup, login, delete user, add song, delete song, view song list, list all users, list all songs, create role, and create profile
- How we did it: pair programming
- Challenges: 1. sometimes we need to redesign the controller signature to collect adequate info to finish the operation, which needs to redo the following layers 2. the many-to-many, one-to-many types of join table need more time to understand the relationship
- What we like: the structure is well-designed to emphasize diffrent responsibility of different layers

Thr- Sun: step 4: Spring Security
- What we did: set up the spring request filter, and the components to activate the filter
- Design Decision:
- - we found a issue like: user1 can add song for user2, user2 can create profile for user3, which is improper, we did research and found a way to user @PreAuthorize("#username == authentication.name") to authorize only user authorized in the token can manipulate the API for a specific function.
- How we did it: pair programming
- Challenges: 1. the components are sparse, which needs more time to configure. 2. we mistakenly imported a SecurifyConfig class from other library, we need Isha's help to find that culprit.
- What we like: we collaborated together to debug the issue and solve it.

Sat-Sun: step 5: mocking and stubbing unit tests
- What we did: finished mocking unit tests for user, and stubbing unit tests for user profile. Tested all gets and puts in postman.
- Design Decision: no much design
- How we did it: independent programming
- Challenges: 1. some tests may require multiple components to collaborate 2. the way to mock session.get() and session.delete() 3. it is hard to think about all cases to cover. 4. postman testing uncovered many coding challenges
- What we like: writing the tests refreshed the understanding of spring web app structure.
