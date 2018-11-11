<template>
  <section class="recipe-ingredients">
    <div class="recipe-ingredients__title">Ingredients</div>
    <div class="ingredients-list" v-if="!isIngrendientsStringArray">
      <div class="ingredients-list__content">
        <div v-for="(items, index) in ingredients" :key="index">
          <h3 class="ingredients-list__group-title">{{items.title}}</h3>
          <ul class="ingredients-list__group">
            <li class="ingredients-list__item" v-for="(item,index1) in items.list" :key="index1">{{item}}</li>
          </ul>
        </div>
      </div>
    </div>
    <div class="ingredients-list" v-else>
      <div class="ingredients-list__content">
        <ul class="ingredients-list__group">
          <li class="ingredients-list__item" v-for="(item,index) in ingredients" :key="index">{{item}}</li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    ingredients: {
      type: Array,
      default: new Array(),
    },
  },
  mounted() {
    console.log(this.ingredients)
  },
  computed: {
    isIngrendientsStringArray() {
      return this.ingredients[0].constructor.toString().includes('String')
    },
  },
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
