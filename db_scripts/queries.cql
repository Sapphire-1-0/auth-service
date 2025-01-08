-- Counter node creation cyphers
//Create Counter node
CREATE (c:Counter{name:"productIdCounter", value:1000}) return c;

-- Authority Node creation Cyphers
//Creating Authority node
Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"authority.delete"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

-- Role Node creation Cyphers
//Create Role node
Match (c:Counter {name:"roleIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Role {roleId:newValue, roleName:"admin"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

-- User Node creation Cyphers
//Create User node
Match (c:Counter {name:"userIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:User {userId:newValue, userName:"john", password:"$2a$10$q15whOtuMFuQIWqRNzzLzezI7.DURBkOL773py64tMdf6DN.x8IgG", accountNotExpired: true, accountNotLocked: true, credentialsNotExpired: true, enabled:true})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

-- Role Authority Relationship Cypher
//Role-Authority-Relationship
match (r:Role{roleName:"admin"})
match (ur:Authority{permission:"user.read"})
match (uw:Authority{permission:"user.write"})
match (ud:Authority{permission:"user.delete"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

-- User Role Relationship Cypher
//User-Role-Relationship
match (u:User{userName:"john"})
match (r:Role{roleName:"admin"})
create (u)-[:HAS_ROLE]->(r)
return r, u;