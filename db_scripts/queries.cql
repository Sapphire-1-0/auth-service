-- Counter node creation cyphers
//Create Counter node
CREATE (c:Counter{name:"authorityIdCounter", value:1000}) return c;
CREATE (c:Counter{name:"roleIdCounter", value:1000}) return c;
CREATE (c:Counter{name:"userIdCounter", value:1000}) return c;
CREATE (c:Counter{name:"productIdCounter", value:1000}) return c;

-- Authority Node creation Cyphers
//Creating Authority node
Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"authority.read"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"authority.write"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"authority.delete"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"role.read"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"role.write"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"role.delete"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"user.read"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"user.write"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"user.delete"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"account.read"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"account.write"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"account.delete"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"provider.write"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"provider.read"})
CREATE (c)-[:GENERATED]->(a)
RETURN a,c;

Match (c:Counter {name:"authorityIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Authority {authorityId:newValue, permission:"provider.delete"})
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

Match (c:Counter {name:"roleIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:Role {roleId:newValue, roleName:"customer-specialist"})
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

Match (c:Counter {name:"userIdCounter"})
with c, c.value + 1 as newValue
SET c.value = newValue
CREATE (a:User {userId:newValue, userName:"mary", password:"$2a$12$s4imFtoSN8JqhtG2AckC3eIlear87YxWxg4UMcTEph/FTWNxLXjs2", accountNotExpired: true, accountNotLocked: true, credentialsNotExpired: true, enabled:true})
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

match (r:Role{roleName:"admin"})
match (ur:Authority{permission:"role.read"})
match (uw:Authority{permission:"role.write"})
match (ud:Authority{permission:"role.delete"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

match (r:Role{roleName:"admin"})
match (ur:Authority{permission:"authority.read"})
match (uw:Authority{permission:"authority.write"})
match (ud:Authority{permission:"authority.delete"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

match (r:Role{roleName:"admin"})
match (ur:Authority{permission:"account.read"})
match (uw:Authority{permission:"account.write"})
match (ud:Authority{permission:"account.delete"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

match (r:Role{roleName:"admin"})
match (ur:Authority{permission:"provider.read"})
match (uw:Authority{permission:"provider.write"})
match (ud:Authority{permission:"provider.delete"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

match (r:Role{roleName:"customer-specialist"})
match (ur:Authority{permission:"user.read"})
match (uw:Authority{permission:"role.read"})
match (ud:Authority{permission:"authority.read"})
create (r)-[:HAS_AUTHORITY]->(ur)
create (r)-[:HAS_AUTHORITY]->(uw)
create (r)-[:HAS_AUTHORITY]->(ud)
return r, ur, uw, ud;

match (r:Role{roleName:"customer-specialist"})
match (ur:Authority{permission:"account.read"})
create (r)-[:HAS_AUTHORITY]->(ur)
return r, ur;

match (r:Role{roleName:"customer-specialist"})
match (ur:Authority{permission:"provider.read"})
create (r)-[:HAS_AUTHORITY]->(ur)
return r, ur;

-- User Role Relationship Cypher
//User-Role-Relationship
match (u:User{userName:"john"})
match (r:Role{roleName:"admin"})
create (u)-[:HAS_ROLE]->(r)
return r, u;

match (u:User{userName:"mary"})
match (r:Role{roleName:"customer-specialist"})
create (u)-[:HAS_ROLE]->(r)
return r, u;

--- Create Resources for Provider Management
--- Resource - Get all organizations
MATCH (a:Authority {permission: "provider.read"})

CREATE (r:Resource {
  resourceId: "RES-743ERT87FG",
  resourceName: "GET_ALL_PROVIDERS",
  resourceType: "PROVIDER",
  resourceURI: "/api/v1/sapphire/provider/private/get-providers",
  resourceDescription: "Get all providers in the system",
  resourceOwner: "PROVIDER_MANAGEMENT"
})

CREATE (r)-[:NEEDS_AUTHORITY]->(a)
RETURN r, a

--- Resource - Create an Organization
MATCH (a:Authority {permission: "provider.read"})

CREATE (r:Resource {
  resourceId: "RES-743ERT87FG",
  resourceName: "GET_ALL_PROVIDERS",
  resourceType: "PROVIDER",
  resourceURI: "/api/v1/sapphire/provider/private/get-providers",
  resourceDescription: "Get all providers in the system",
  resourceOwner: "PROVIDER_MANAGEMENT"
})

CREATE (r)-[:NEEDS_AUTHORITY]->(a)
RETURN r, a