<template>
    <div class="container is-fullhd">
     <section class="hero">
  <div class="hero-body">
    <div class="container">
      <h1 class="title">
        {{msg}}
      </h1>
      <h2 class="subtitle">
       Create Recipes
      </h2>
       <h2 class="subtitle">
       Search Recipes
      </h2>
    </div>
  </div>
</section>
    </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
