<template>
<Layout>
    <main class="app-content">
        <section class="section is-hero">
            <div class="container">
                <div class="columns is-vcentered">
                    <div class="column is-6 is-5-fullhd is-offset-1-full-hd has-padding-left-xl has-padding-right-xl">
                        <div class="section-header">
                            <h1 class="title is-spaced">Recipe World for everyone</h1>
                            <h2 class="subtitle is-3">A recipe has no soul. you as the cook must bring soul to the recipe.</h2>
                            <div class="field is-grouped">
                                <div class="control"> <a class="button is-primary is-medium" href="#platform">How does it work?</a> </div>
                                <div class="control">
                                    <router-link class="button is-primary is-medium is-outlined" to="/create">Create recipe</router-link>
                                </div>
                                <div class="control">
                                    <router-link to="/library" class="button is-primary is-medium is-outlined">Explore Library</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="column is-6">
                        <section class="section-media">
                            <img role="presentation" src="@assets/images/recipes1.png" />
                        </section>
                    </div>
                </div>
            </div>
        </section>

        <section class="section is-usp has-background is-medium">
            <div class="container">
                <div class="columns">
                    <div class="column is-4">
                        <div class="card is-usp"> <img role="presentation" src="@assets/images/logo.png">
                            <hr class="spacer is-2">
                            <h3 class="title is-5 is-spaced"> Ingredient based search </h3>
                            <p class="subtitle is-6"> Storytelling plays a vital role when growing up. Create lasting memories and embark on fantastic journeys with beloved characters. </p>
                        </div>
                    </div>
                    <div class="column is-4">
                        <div class="card is-usp"> <img role="presentation" src="@assets/images/logo.png">
                            <hr class="spacer is-2">
                            <h3 class="title is-5 is-spaced"> Cuisine based search </h3>
                            <p class="subtitle is-6"> We deeply care about diversity and empowerment - each Storyworld contains educational aspects and storytelling tips to teach valuable life lessons. </p>
                        </div>
                    </div>
                    <div class="column is-4">
                        <div class="card is-usp"> <img role="presentation" src="@assets/images/logo.png">
                            <hr class="spacer is-1">
                            <h3 class="title is-5 is-spaced"> Diet based search </h3>
                            <p class="subtitle is-6"> Each bedtime story includes an easy-to-remember summary of Storypoints and the interactive Storybuilder generates millions of exciting storylines. </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

</Layout>
</template>

<script>
import appConfig from '@src/app.config'
import Layout from '@layouts/main'
export default {
    page: {
        title: 'Home',
        meta: [{
            name: 'description',
            content: appConfig.description,
        }, ],
    },
    components: {
        Layout,
    },
}
</script>

<style lang="scss" scoped>
.has-background {
    background-color: #FBFBFD;
}

.card.is-usp {
    max-width: 320px;
    margin: 0 auto
}

.card.is-usp .subtitle {
    line-height: 1.66667
}

.card {
    background-color: transparent;
    box-shadow: none;
    color: #998DA0;
    max-width: 100%;
    position: relative;
}
</style>