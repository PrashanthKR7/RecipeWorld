<template>
  <Layout>
    <article class="container is-fullheight hero-body recipe">
      <RecipeHeader></RecipeHeader>
      <div class="grid-padding">
        <div class="columns recipe-content">
          <IngredientsList class="column" :ingredients="ingredients" />
          <RecipeInstructions class="column" :instructions="steps" />
        </div>
      </div>
    </article>
  </Layout>
</template>

<script>
import Layout from '@layouts/main'
import RecipeHeader from '@components/recipe/recipe-header'
import IngredientsList from '@components/recipe/ingredients-list'
import RecipeInstructions from '@components/recipe/recipe-instructions'
export default {
  components: {
    Layout,
    RecipeHeader,
    IngredientsList,
    RecipeInstructions,
  },
  data() {
    return {
      ingredients: [
        {
          'For the dough': [
            {
              quantity: '200g',
              name: 'strong white bread flour, plus a little for dusting',
            },
            { quantity: '½ tsp', name: 'fast-action dried yeast' },
            { quantity: '¼ tsp', name: 'golden caster sugar' },
            { quantity: 'a little', name: 'oil, for greasing' },
          ],
        },
        {
          'For the toppings': [
            { quantity: '4 tbsp', name: ' olive oil, plus a little extra' },
            {
              quantity: '1',
              name: ' garlic clove, thinly sliced',
            },
            {
              quantity: '200g',
              name: ' passata',
            },
            {
              quantity: 'pinch of',
              name: ' golden caster sugar(optional)',
            },
            {
              quantity: '1 small',
              name: ' aubergine, sliced into discs',
            },
            {
              quantity: '100g',
              name: ' ricotta',
            },
            {
              quantity: 'small handful',
              name: ' mint, roughly chopped',
            },
            { quantity: 'extra', name: ' virgin olive oil, for drizzling' },
          ],
        },
      ],
      steps: [
        'Weigh the ingredients for the dough into a large bowl and add 1/2 tsp salt and 125ml warm water. Mix to form a soft dough, then tip onto your work surface and knead for 5 mins or until the dough feels stretchy. Clean and grease the bowl and return the dough. Cover with cling film and leave somewhere warm to rise for 1 hr, or until the dough has doubled in size.',
        'Meanwhile, make the sauce. Heat 1 tbsp olive oil in a pan and add the garlic. Sizzle gently for 30 secs, making sure the garlic doesn’t brown, then add the passata. Season well and bubble for 8-10 mins until you have a rich sauce – add a pinch of sugar if it tastes a little too tart. Set aside.',
        'When the dough has risen, knock out the air and roll it into a pizza base the same size as a large frying pan. Oil the surface of the dough, cover with cling film , then leave on the work surface for 15 mins to puff up a little. Meanwhile, heat 2 tbsp oil in the frying pan and add the aubergines in a single layer (you may have to cook in batches). Season well and cook for 4-5 mins on each side until really tender and golden. Transfer to a dish and cover with foil to keep warm.',
        'Heat the remaining 1 tbsp of oil in the pan and carefully lift the dough into it. You may have to reshape it a little to fit. Cook over a low-medium heat until the underside is golden brown and the edges of the dough are starting to look dry and set – this should take about 6 mins, but it’s best to go by eye. Flip over, drizzle a little more oil around the edge of the pan so it trickles underneath the pizza base, and cook for another 5-6 mins until golden and cooked through. Reheat the sauce if you need to and spread it over the base. Top with the warm aubergines and dot with spoonfuls of ricotta. Scatter with mint and drizzle with a little extra virgin olive oil just before serving.',
      ],
    }
  },
}
</script>

<style lang="scss" scoped>
@import '@design';
.grid-padding {
  border: 0.6rem solid rgba($info, 0.15);
  padding: 1rem;
}

.recipe-content > div {
  border-bottom: 1px dotted $grey-light;
  padding-bottom: 0.6rem;
  font-size: 1.5rem;
  font-weight: 300;
}
</style>
