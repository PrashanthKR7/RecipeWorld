<template>
  <router-link :to="`/recipes/${recipe.id}`">
    <div class="column is-narrow has-padding-sm">
      <div class="card">
        <div class="card-image">
          <figure class="image is-16by9">
            <img src="./../../assets/images/sample.png" alt="Placeholder image">
          </figure>
          <div class="is-overlay is-ancestor is-vertical card-side-overlay">
            <div class="tile is-vertical">
              <b-icon class="is-veg" icon="radiobox-marked">
              </b-icon>
              <p>Veg</p>
            </div>
            <div class="tile is-vertical">
              <b-icon class="is-heart" icon="heart">
              </b-icon>
              <p>10k</p>
            </div>
            <div class="tile is-vertical">
              <b-icon class="is-meter" icon="altimeter">
              </b-icon>
              <p>easy</p>
            </div>

          </div>
          <div class="is-overlay is-size-6 card-overlay columns is-mobile is-marginless">
            <div class="column is-narrow has-padding-right-none">
              <b-icon icon="clock-outline">
              </b-icon>
            </div>
            <div class="column columns is-mobile is-marginless has-padding-right-none">
              <div class="column is-paddingless">prep :</div>
              <div class="column is-paddingless is-narrow">20 min</div>
            </div>
            <div class="column columns is-mobile is-marginless has-padding-right-none">
              <div class="column is-paddingless">cook :</div>
              <div class="column is-paddingless is-narrow">10 min</div>
            </div>
          </div>
        </div>
        <div class="card-content">
          <div class="media">

            <div class="media-content">
              <p class="title is-5 has-ellipsis">Pizza</p>
              <p class="subtitle is-6">@prasanth</p>
            </div>
          </div>

          <div class="content">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris. <a>@bulmaio</a>.
            <a href="#">#css</a> <a href="#">#responsive</a>
            <br>
            <time datetime="2016-1-1">11:09 PM - 1 Jan 2016</time>
          </div>
        </div>
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: 'RecipeCard',
  props: {
    recipe: {
      type: Object,
      required: true,
    },
  },
}
</script>

<style lang='scss' scoped>
@import '@design';

.is-veg {
  color: #42ff42;
}
.is-nonveg {
  color: red;
}
.is-meter {
  color: #cddc39;
}
.is-heart {
  color: #ffc107;
}

.card {
  max-width: 300px;
  cursor: pointer;
  border-radius: 1rem;
}

.card-overlay {
  background-color: rgba($primary, 0.5);
  color: $primary-invert;
  height: 25%;
  width: 84%;
  top: auto !important;
  text-shadow: 2px 2px 2px #273b7a;
}

.card-side-overlay {
  background-color: rgba($primary, 0.5);
  color: $primary-invert;
  height: 100%;
  width: 16%;
  left: auto !important;
  bottom: auto !important;
  text-shadow: 2px 2px 2px #273b7a;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  justify-content: space-evenly;
  text-align: center;
  padding-top: 0.5rem;
  border-radius: 0 1rem 0 0;

  .tile {
    font-size: 0.8rem;
  }
}

.tile {
  justify-content: center;
  text-align: center;
  align-items: center;
}

img {
  border-radius: 1rem 1rem 0 0;
}

.card-content {
  background-color: #5ea6ea14;
}

.has-padding-right-none {
  padding-right: 0px;
  align-items: center;
}
</style>
