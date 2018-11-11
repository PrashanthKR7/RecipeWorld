<template>
  <header class="columns is-paddingless is-marginless recipe-header">
    <div class="column is-4">
      <figure class="image is-4by3">
        <img class="recipe-header__cover-image" src="//www.bbcgoodfood.com/sites/default/files/styles/recipe/public/recipe/recipe-image/2016/09/frying-pan-pizza.jpg?itok=ELbVlLN7" alt="Frying pan pizza with aubergine, ricotta &amp; mint" title="Frying pan pizza with aubergine, ricotta &amp; mint">
      </figure>
      <div class="columns is-mobile">
        <div class="column column_button">
          <button class="button is-success is-outlined">Save to cook book</button>
        </div>
        <div class="column column_button">
          <router-link to="/create-edit/1" class="button is-info">Edit</router-link>
        </div>
        <div class="column column_button">
          <button class="button is-primary">Print</button>
        </div>
      </div>
    </div>

    <div class="column is-8 is-ancestor">
      <div class="tile">
        <div class="container">
          <h1 class="title is-3 recipe-header__title">
            Frying pan pizza with aubergine, ricotta &amp; mint
          </h1>
          <div class="tile is-ancestor has-padding-left-md">
            <div class="has-margin-right-lg">
              <rating :items="items" :value="value" @change="update"></rating>
            </div>
            <div class="has-margin-right-lg">By
              <span class="author">
                <a href="/chef/cassie-best">Cassie Best</a>
              </span>
            </div>
            <div>By </div>

          </div>
          <div class="tile is-uppercase is-size-7">
            <div class="column columns is-mobile is-3">
              <div class="column is-narrow">
                <b-icon icon="clock-outline"></b-icon>
              </div>
              <div class="column tile is-ancestor is-vertical recipe-header__time">
                <div class="tile"><span>Prep: </span> <span>20min</span></div>
                <div class="tile"><span>Cook: </span> <span>20min</span></div>
              </div>
            </div>
            <div class="column columns is-mobile is-3">
              <div class="column is-narrow">
                <b-icon icon="altimeter"></b-icon>
              </div>
              <div class="column"> Easy </div>
            </div>
            <div class="column columns is-mobile is-3">
              <div class="column is-narrow">
                <b-icon icon="altimeter"></b-icon>
              </div>
              <div class="column"> serves 2 </div>
            </div>
            <div class="column columns is-mobile is-3">
              <div class="column is-narrow">
                <b-icon class="is-veg" icon="radiobox-marked">
                </b-icon>
              </div>
              <div class="column">Vegetarian</div>
            </div>
          </div>
          <div class="tile">
            <div class="field-item even">
              <p>This recipe ensures a crispy-bottomed pizza without ever turning on the oven. It's easy, veggie and 2 of your 5-a-day</p>
            </div>
          </div>
        </div>
      </div>

      <div class="recipe-header__bottom">
        <section>
          <NutritionList v-if="nutritions"></NutritionList>
        </section>

      </div>
    </div>

  </header>
</template>

<script>
import Rating from '@components/common/rating'
import NutritionList from '@components/recipe/nutrition-list'
export default {
  components: {
    Rating,
    NutritionList,
  },
  data() {
    return {
      nutritions: [],
      value: 2,
      items: [
        {
          title: 'Amazing',
          value: 5,
        },
        {
          title: 'Very good',
          value: 4,
        },
        {
          title: 'Average',
          value: 3,
        },
        {
          title: 'Not good',
          value: 2,
        },
        {
          title: 'Terrible',
          value: 1,
        },
      ],
    }
  },
  props: {},
  methods: {
    update(val) {
      this.value = val
    },
  },
}
</script>

<style lang="scss" scoped>
@import '@design';
.recipe-header {
  background: rgba($info, 0.15);
  border-radius: 1.25rem 1.25rem 0 0;
  .recipe-header__cover-image {
    border-radius: 1.25rem;
  }
  .recipe-header__title {
    font-weight: 400;
  }
  .recipe-header__time span:first-child {
    padding-right: 0.2rem;
  }
  .column_button {
    padding-top: 1.25rem;
    .button {
      width: 100%;
    }
  }
}
</style>
