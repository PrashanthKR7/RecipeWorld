<template>
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
              <div class="control"> <router-link to="/library" class="button is-primary is-medium is-outlined">Explore Library</router-link> </div>
            </div>
          </div>
        </div>
        <div class="column is-6">
          <section class="section-media">
            <img role="presentation" src="../assets/images/recipes1.png" />
          </section>
        </div>
      </div>
    </div>
  </section>

  <section class="section is-usp has-background is-medium">
    <div class="container">
      <div class="columns">
        <div class="column is-4">
          <div class="card is-usp"> <img role="presentation" src="/assets/images/icons/family-c0f52dde.svg?1540530871">
            <hr class="spacer is-1">
            <h3 class="title is-4 is-spaced"> Quality Family Time </h3>
            <p class="subtitle is-5"> Storytelling plays a vital role when growing up. Create lasting memories and embark on fantastic journeys with beloved characters. </p>
          </div>
        </div>
        <div class="column is-4">
          <div class="card is-usp"> <img role="presentation" src="/assets/images/icons/bookshelf-b83dab6e.svg?1540530871">
            <hr class="spacer is-1">
            <h3 class="title is-4 is-spaced"> Teach Valuable Life Lessons </h3>
            <p class="subtitle is-5"> We deeply care about diversity and empowerment - each Storyworld contains educational aspects and storytelling tips to teach valuable life lessons. </p>
          </div>
        </div>
        <div class="column is-4">
          <div class="card is-usp"> <img role="presentation" src="/assets/images/icons/book-96ff6ee1.svg?1540530871">
            <hr class="spacer is-1">
            <h3 class="title is-4 is-spaced"> A Safety Net for Storytelling </h3>
            <p class="subtitle is-5"> Each bedtime story includes an easy-to-remember summary of Storypoints and the interactive Storybuilder generates millions of exciting storylines. </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
</template>

<script>
export default {
  name:'Intro'
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.subtitle.is-3 {
  color: #998DA0;
  font-weight: 400;
  line-height: 1.25;
  font-size: 1.5rem;
}
</style>
