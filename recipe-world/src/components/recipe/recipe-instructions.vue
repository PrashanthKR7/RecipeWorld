<template>
  <section id="recipe-instruction" class="responsive-tabs__pane">
    <div class="recipe-instruction__title">Instructions</div>
    <div class="instruction">
      <ol class="instruction__list">
        <RecipeInstruction v-for="(step,index) in instructions" :key="index" :instruction="step" />
      </ol>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    instructions: {
      type: Array,
      default: new Array(),
    },
  },
}
</script>

<style scoped>
.instruction {
  margin-bottom: 2rem;
  margin-top: 0.6rem;
  padding-bottom: 0.6rem;
}
.instruction__list {
  list-style-type: none;
}
.recipe-instruction__title {
  border-bottom: 1px dotted lightgray;
  padding-bottom: 0.6rem;
  font-size: 1.5rem;
  font-weight: 300;
}
</style>
