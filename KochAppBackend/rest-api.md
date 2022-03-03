<h3>REST-API-Documentation</h3>

REST resources:
- Recipes
- App-User

Get all recipes:   
GET /api/recipes

Get a recipe:  
GET /api/recipes/{id}

Create a new recipe:   
POST /api/recipes

Delete a recipe:  
DELETE /api/recipes/{id}

Update a recipe:  
PUT /api/recipes/{id}

Update a tag:  
PUT /api/recipes/{id}/tags

Get all recipes with specific tag:  
GET /api/recipes?tag={tag}
