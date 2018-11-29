<template>
  <section class="recipe-ingredients">
    <div class="recipe-ingredients__title" v-if="showTitle">Ingredients</div>
    <div class="ingredients-list">
      <div class="ingredients-list__content">
        <div v-for="(items, key, index) in ingredients" :key="index">
          <h3 class="ingredients-list__group-title">{{key}}</h3>
          <ul class="ingredients-list__group">
            <li class="ingredients-list__item" v-for="(item,index1) in items" :key="index1">
              <div class="columns">
                <div class="column"> {{item.quantity+" "+item.name}}</div>
                <div class="column is-narrow" v-if="allowRemove">
                  <button @click.prevent="removeIngredient(item.id)" class="button is-small">
                    <b-icon icon="minus-box"></b-icon>
                  </button>
                </div>
              </div>

            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    ingredients: {
      type: Object,
      required: true,
    },
    showTitle: {
      type: Boolean,
      default: false,
    },
    allowRemove: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    removeIngredient(id) {
      this.$emit('onRemovingIngredient', id)
    },
  },
  mounted() {},
}
</script>

<style lang="scss" scoped>
@import '@design';
.recipe-ingredients__title {
  border-bottom: 1px dotted lightgray;
  padding-bottom: 0.6rem;
  font-size: 1.5rem;
  font-weight: 300;
}

.ingredients-list {
  margin-bottom: 2rem;
  margin-top: 0.6rem;
  padding-bottom: 0.6rem;
}

.ingredients-list__content {
  padding: 4%;
  border: 1px solid #eaeaea;
  border-bottom: 0;
  position: relative;
}

.ingredients-list__content:after {
  content: '';
  position: absolute;
  bottom: -11px;
  left: 0;
  width: 100%;
  height: 12px;
  background: url('./../../assets/images/zig-zag.png') repeat-x left center;
  -webkit-box-shadow: 0 6px 17px -6px #666;
  box-shadow: 0 6px 17px -6px #666;
}

.ingredients-list__group-title {
  color: #59a399;
  font-size: 18px;
  margin-bottom: 5px;
}

.ingredients-list__group {
  margin: 0;
  list-style: none;
}

.ingredients-list__group ul {
  margin-left: 0;
  list-style: none;
}

.ingredients-list__item {
  padding: 0.4rem 0.6rem;
  line-height: 1.2rem;
}

.ingredients-list__item:nth-child(odd) {
  background: #f1f7f7;
}
</style>
