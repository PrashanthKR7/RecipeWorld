
<template>
  <div class="recipe-header__nutrition">
    <h3 class="recipe-header__nutrition-title"><strong>Nutrition:</strong> per serving</h3>
    <ul class="nutrition" itemprop="nutrition" itemscope="" itemtype="http://schema.org/NutritionInformation">
      <li><span class="nutrition__label">kcal</span><span class="nutrition__value" itemprop="calories">721</span></li>
      <li><span class="nutrition__label">fat</span><span class="nutrition__value" itemprop="fatContent">31g</span></li>
      <li><span class="nutrition__label">saturates</span><span class="nutrition__value" itemprop="saturatedFatContent">7g</span></li>
      <li><span class="nutrition__label">carbs</span><span class="nutrition__value" itemprop="carbohydrateContent">85g</span></li>
      <li><span class="nutrition__label">sugars</span><span class="nutrition__value" itemprop="sugarContent">10g</span></li>
      <li><span class="nutrition__label">fibre</span><span class="nutrition__value" itemprop="fiberContent">9g</span></li>
      <li><span class="nutrition__label">protein</span><span class="nutrition__value" itemprop="proteinContent">20g</span></li>
      <li><span class="nutrition__label">salt</span><span class="nutrition__value" itemprop="sodiumContent">1.4g</span></li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {},
}
</script>

<style scoped>
.nutrition {
  margin: 0.6rem 0 0;
  width: auto;
  letter-spacing: -3px;
}

.nutrition > li {
  line-height: 1.8;
  letter-spacing: 0;
  display: inline-block;
  text-align: center;
  margin: 0 5px 8px 0;
  min-width: 57px;
}

.nutrition__label {
  font-family: 'Helvetica Neue', Helvetica, Arial;
  font-size: 0.8rem;
  line-height: 1.2rem;
  color: #767676;
  font-style: normal;
  font-size: 0.7rem;
  background: #eec3b0;
  border-radius: 3px 3px 0 0;
  color: #333;
  position: relative;
  display: block;
  padding: 3px 0;
}

.nutrition__value {
  font-family: 'Helvetica Neue', Helvetica, Arial;
  font-size: 12px;
  line-height: 18px;
  color: #767676;
  font-style: normal;
  display: block;
  background: #fff;
  font-size: 11px;
  border-radius: 0 0 3px 3px;
  padding: 2px 0 1px;
}
</style>
