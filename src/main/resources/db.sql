SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS liked_recipes;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE category (
id BIGINT NOT NULL AUTO_INCREMENT
, name VARCHAR(100) NOT NULL
, PRIMARY KEY (id)
);

INSERT INTO category (name) VALUES ("starter"), ("main"), ("dessert");

CREATE TABLE recipe (
id BIGINT NOT NULL AUTO_INCREMENT
, name VARCHAR(50) NOT NULL
, ingredients VARCHAR(500) NOT NULL
, instructions VARCHAR(1000) NOT NULL
, categoryid BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (categoryid) REFERENCES category(id)
);

INSERT INTO recipe (name, ingredients, instructions, categoryid) 
VALUES ('Vegetarian ramen', '80g pack instant noodles (look for an Asian brand with a flavour like sesame), 2 spring onions (finely chopped), ½ head pak choi, 
1 egg, 1 tsp sesame seeds, chilli sauce', 'STEP 1: Cook the noodles with the sachet of flavouring provided (or use stock instead of the sachet, if you have it). 
Add the spring onions and pak choi for the final min. STEP 2: Meanwhile, simmer the egg for 6 mins from boiling, run it under cold water to stop it cooking, 
then peel it. Toast the sesame seeds in a frying pan. STEP 3: Tip the noodles and greens into a deep bowl, halve the boiled egg and place on top. 
Sprinkle with sesame seeds, then drizzle with the sauce or sesame oil provided with the noodles, and chilli sauce, if using.', 2),
('Couscous salad', '100g couscous, 200ml hot low salt vegetable stock, 2 spring onions, 1 red pepper, ½ cucumber, 50g feta cheese, 2tbsp pesto, 2tbsp pine nuts',
'STEP 1: Tip the couscous into a large bowl and pour over the stock. Cover, then leave for 10 mins until fluffy and all the stock has been absorbed. 
Meanwhile, slice the onions and pepper, and dice the cucumber. Add these to the couscous, fork through the pesto, crumble in the feta, then sprinkle over 
pine nuts to serve.', 1), ('Frozen berry yogurt', '250g frozen mixed berry, 250g Arla Protein Strawberry yogurt, 1tbsp honey or agave syrup', 
'STEP 1: Blend berries, Arla Protein Strawberry yogurt and honey or agave syrup in a food processor for 20 seconds, until it comes together to a smooth 
ice-cream texture. Scoop into bowls and serve.', 3);

CREATE TABLE app_user (
id BIGINT NOT NULL AUTO_INCREMENT
, username VARCHAR(50) NOT NULL
, password VARCHAR(100) NOT NULL
, role VARCHAR(10) NOT NULL
, PRIMARY KEY (id)
);

INSERT INTO app_user (username, password, role) 
VALUES ('user', '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER')
, ('admin','$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN');

CREATE TABLE liked_recipes (
appuser_id BIGINT NOT NULL
, recipe_id BIGINT NOT NULL
, PRIMARY KEY (appuser_id, recipe_id)
);

INSERT INTO liked_recipes (appuser_id, recipe_id)
VALUES (1, 2), (1,3);

SELECT * FROM recipe;
SELECT * FROM category;
SELECT * FROM app_user;
SELECT * FROM liked_recipes;
