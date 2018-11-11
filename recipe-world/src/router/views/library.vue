<template>
  <Layout>
    <div class="container is-fullheight hero-body">
      <Search></Search>
      <section class="cards">
        <div class="columns is-multiline is-mobile is-centered">
          <RecipeCard :recipe="recipe" :key="recipe.id" v-for="recipe in filteredRecipes" />
        </div>
      </section>
      <Pagination :total="totalRecipes" :per-page="perPage" @page-changed="pageChange"></Pagination>
    </div>
  </Layout>
</template>

<script>
import Layout from '@layouts/main'
import Search from '@components/common/search-bar'
import Pagination from '@components/common/pagination'
import RecipeCard from '@components/common/recipe-card'
export default {
  page: {
    title: 'library',
    meta: [
      {
        name: 'description',
        content: `Library of all recipes in the world`,
      },
    ],
  },
  components: {
    Layout,
    Search,
    Pagination,
    RecipeCard,
  },
  data() {
    return {
      perPage: 6,
      currentPage: 1,
      recipes: [
        {
          id: 1,
          name: 'Buffalo Cauliflower Bites',
          description:
            "I love this recipe because it's a simple upgrade to the usual tailgating finger food. You still get the spicy flavor, crispy crunch and creamy sauce you want from a wing, but with a healthier twist.",
          cookingTime: 40,
          cookingDifficulty: 'easy',
          ingredients: [
            {
              name: 'red jalapeños, stems removed',
              quantity: '2',
            },
            {
              name: 'vegetable oil',
              quantity: '1 cup',
            },
            {
              name: 'vegetable oil',
              quantity: '1 cup',
            },
            {
              name: 'chili oil',
              quantity: '1/2 cup',
            },
            {
              name: 'lime juice',
              quantity: '1/2 cup',
            },
            {
              name: 'salt',
              quantity: '1 tablespoon',
            },
            {
              name: 'cauliflower florets',
              quantity: '2 cups',
            },
            {
              name: 'buttermilk',
              quantity: '1 cup',
            },
            {
              name: 'cornstarch',
              quantity: '1 cup',
            },
            {
              name: 'ground black pepper',
              quantity: 'to taste',
            },
          ],
          cuisine: {
            id: 1,
            name: 'Continental',
          },
          mealType: {
            id: 1,
            name: 'dinner',
          },
          steps: [
            {
              content:
                'In a small pan add oil and garlic and cook until the garlic is crispy but not burnt.',
              image: '',
            },
            {
              content:
                'In a small bowl or stand mixer whip together the garlic and oil with the remainder of the ingredients until light and airy.',
              image: '',
            },
            {
              content:
                'Season with salt and pepper and set aside at room temperature until ready to use.',
              image: '',
            },
            {
              content:
                'Heat a heavy-bottomed pot, with about 3 inches of oil, to 350 F.',
              image: '',
            },
            {
              content:
                'Coat cauliflower in buttermilk then dredge in cornstarch and lightly fry until crispy about 2-3 minutes.',
              image: '',
            },
            {
              content:
                'Season with salt and pepper and then toss in some of the chili oil to coat.',
              image: '',
            },
            {
              content:
                'Smear a plate or platter with whipped goat cheese then arrange the fried cauliflower on top. Drizzle some extra jalapeño vinaigrette around the plate then garnish with pickled shallots, crushed peppercorns and chervil sprigs.',
              image: '',
            },
          ],
        },
        {
          id: 2,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 3,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 4,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 5,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 6,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 7,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 8,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 9,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 10,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 11,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 12,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 13,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 14,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 15,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 16,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 17,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 18,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 19,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
        {
          id: 20,
          name: 'Recipe1',
          description:
            'asdf asdfasd dsfdgsd fg fgbsdfgargt h54trs dfgsdfgsdf sdfg',
        },
      ],
    }
  },
  computed: {
    // a computed getter
    totalRecipes: function() {
      return this.recipes.length
    },
    filteredRecipes: function() {
      return this.recipes.slice(
        (this.currentPage - 1) * this.perPage,
        this.currentPage * this.perPage
      )
    },
  },
  methods: {
    pageChange: function(value) {
      this.currentPage = value
    },
  },
}
</script>

<style scoped>
</style>
