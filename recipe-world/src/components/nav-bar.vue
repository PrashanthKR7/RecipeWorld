<template>
<header class="app-header">
    <nav class="navbar is-fixed-top">
        <div class="container">
            <div class="navbar-brand">
                <div class="navbar-item">
                    <a class="brand" href="/">
                        <div class="brand-icon"> <img alt="Recipe World" role="presentation" src="../assets/images/recipes 64x64.png"> </div>
                        <div class="brand-content">
                            <div class="brand-title"> Recipe World </div>
                            <div class="brand-subtitle is-4"> Recipes for one &amp; all </div>
                        </div>
                    </a>
                </div>
                <div class="navbar-burger burger" @click="showNav = !showNav" :class="{ 'is-active' : showNav }" data-target="mainNav">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div class="navbar-menu" id="mainNav" :class="{ 'is-active' : showNav }">
                <div class="navbar-end">
                    <router-link to="/" exact class="navbar-item is-active">Home</router-link>
                    <router-link to="/recipes" class="navbar-item">Library</router-link>
                    <router-link to="/logout" v-if="loggedIn" class="navbar-item">Logout</router-link>
                    <router-link to="/login" v-else class="navbar-item">Login</router-link>
                </div>
            </div>
        </div>
    </nav>
</header>
</template>

<script>
import {
    authComputed
} from '@state/helpers'

export default {
    name: 'NavBar',
    data() {
        return {
            showNav: false,
        }
    },
    computed: {
        ...authComputed,
    },
}
</script>

<style scoped>
.app-header .navbar {
    box-shadow: 0 1px 0 0 rgba(142, 81, 199, 0.05);
    transition-duration: 172ms;
    will-change: min-height
}

@media screen and (max-width: 768px) {
    .app-header .navbar {
        min-height: 5rem;
        box-shadow: 0 1px 4px 0 rgba(70, 56, 83, 0.1)
    }
}

.app-header .navbar>.container {
    transition-duration: 172ms;
    will-change: min-height
}

@media screen and (max-width: 768px) {
    .app-header .navbar>.container {
        min-height: 5rem
    }
}

.app-header .navbar .navbar-brand {
    transition-duration: 172ms;
    will-change: min-height
}

@media screen and (max-width: 768px) {
    .app-header .navbar .navbar-brand {
        min-height: 5rem
    }
}

@media screen and (max-width: 768px) {
    .app-header .navbar .brand .brand-icon {
        width: 40px;
        height: 40px
    }
}

.app-header .navbar .navbar-burger {
    width: 5rem;
    height: 5rem
}

.app-header .navbar .navbar-burger span {
    height: 2px;
    border-radius: 99rem
}

.app-header .navbar.is-small {
    min-height: 4rem;
    box-shadow: 0 1px 4px 0 rgba(70, 56, 83, 0.1)
}

.app-header .navbar.is-small>.container {
    min-height: 4rem
}

.app-header .navbar.is-small .navbar-brand {
    min-height: 4rem
}

.app-header .navbar.is-small .brand .brand-icon {
    width: 32px;
    height: 32px;
    margin-right: 0.5rem
}

.app-header .navbar.is-small .brand .brand-title {
    transform: translateY(calc(50% + 0.15rem))
}

.app-header .navbar.is-small .brand .brand-subtitle {
    transform: scaleY(0);
    opacity: 0
}

@media screen and (max-width: 768px) {
    .app-header .navbar-menu {
        text-align: center
    }
    .app-header .navbar-menu .navbar-item {
        font-size: 1.5rem
    }
    .app-header .navbar-menu .navbar-item img {
        max-height: 3rem
    }
}

@media screen and (min-width: 769px),
print {
    .app-header .navbar-end {
        -ms-flex-align: center;
        align-items: center
    }
    .app-header .navbar-menu .navbar-item {
        position: relative;
        padding: 0.875rem 1rem;
        margin-left: 0.1rem;
        margin-right: 0.1rem;
        border-radius: 10px;
        font-size: 1.125rem;
        line-height: 1;
        font-weight: 500;
        transition-duration: 86ms
    }
    .app-header .navbar-menu .navbar-item .tag {
        position: absolute;
        top: -8px;
        left: 100%;
        margin-left: calc(-1rem + 2px);
        margin-top: calc(1rem - 0px)
    }
}

.brand {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-align: center;
    align-items: center;
    line-height: 1
}

.brand .brand-icon {
    width: 56px;
    height: 56px;
    margin-right: 1rem;
    transition-duration: 172ms;
    will-change: width, height, margin-right
}

.brand .brand-icon img {
    display: block;
    max-width: 100%;
    max-height: 100%
}

.brand .brand-title {
    font-size: 1.25rem;
    font-weight: 500;
    color: #463853;
    transition-duration: 172ms;
    will-change: transform
}

.brand .brand-subtitle {
    margin-top: 0.25rem;
    font-size: 1.125rem;
    font-weight: normal;
    color: #998DA0;
    opacity: 1;
    transition-duration: 172ms;
    will-change: opacity, transform
}
</style>