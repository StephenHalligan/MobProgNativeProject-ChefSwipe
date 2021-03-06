# MobProgNativeProject-ChefSwipe
Assignment for Mobile Programming with Native Technologies

This project covers kotlin fundamentals, encompassing the use of Firebase, with aspects Java, xml etc.

The idea behind the project is a recipe app, which makes use of Firebase authentication for user login, alongside Firestore to retrieve recipe data.
This app is built similarly to apps like "Tinder"; in this case, if the user likes the idea recipe, they may "swipe right" to view the full recipe.
They may also "swipe left" to skip the recipe and move onto the next!

The application implements firebase to authenticate users in the registration and login pages, and once the user has been authenticated they are automatically logged in on app startup. The app uses a realtime database and firebase authentication to allow users to log in/register. Screenshots of the authentication page can be found [here](https://imgur.com/a/9EQyYwQ), and also in the "Firebase images folder".

The navigation bar can be used to switch between the pages; allowing the user to view recipe cards, edit profile settings like email or password, or logout of the app.

I used firestore to host all of the recipes, from their name to the link to their instructions. The main activity uses loops to check if the "card stack" of recipes is nearly empty, and if it is, it iterates through the firestore database using indexes to retrieve new recipes; adding them an array. This array then showcases each element or recipe as it's own card. Screenshots of this can be found [here](https://imgur.com/a/ZkfleGu), and also in the "Firebase images folder".
