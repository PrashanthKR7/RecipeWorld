import Vue from 'vue';
import VueRouter  from 'vue-router';
import Intro from '@/components/Intro'
import Home from '@/components/Home'
import Login from '@/components/Login'
import Register from '@/components/Register'
import NewRecipe from '@/components/NewRecipe'
import RecipeLibrary from '@/components/RecipeLibrary'
import RecipeDetails from '@/components/RecipeDetails'

Vue.use(VueRouter);

let router = new VueRouter({
	mode: 'hash',
	base: __dirname,
    routes: [       
		{
			path: '/',
			name: 'Intro',
			component: Intro,
		},
		{
			path: '/home',
			name: 'Home',
			component: Home,
		},
		{
			path: '/login',
			name: 'Login',
			component: Login
		},
		{
			path: '/register',
			name: 'Register',
			component: Register
		},
		{
			path: '/add-recipe',
			name: 'NewRecipe',
			component: NewRecipe,
			meta: {
				requiresAuth: true
			}
        },
		{
			path: '/library',
			name: 'RecipeLibrary',
			component: RecipeLibrary,
			props: true,
			meta: {
				requiresAuth: false
			}
		},
		{
			path: '/recipe-detail',
			name: 'RecipeDetails',
			component: RecipeDetails,
			props: true,
			meta: {
				requiresAuth: false
			}
		}
	]
})


router.beforeEach((to, from, next) => {

	let currentUser = JSON.parse(localStorage.getItem('currentUser'));

	let requiresAuth = to.matched.some(record => record.meta.requiresAuth);

	if (requiresAuth && !currentUser) {
		next('/')
	} else if (!requiresAuth && currentUser) {
		next('home')
	} else {
		next()
	}

})

export default router;