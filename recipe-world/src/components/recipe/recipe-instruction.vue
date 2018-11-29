<template>
  <li class="instruction__item">
    <div class="columns">
      <div class="column">{{instruction.content}}</div>
      <div class="column is-narrow" v-if="instruction.image">
        <img class="instruction__item-image" :src="instruction.image" :title="instruction.imageFile.name" />
      </div>
      <div class="column is-narrow" v-if="allowRemove">
        <p class="control">
          <button @click.prevent="removeStep(instruction.id)" class="button is-small">
            <b-icon icon="minus-box"></b-icon>
          </button>
        </p>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    allowRemove: {
      type: Boolean,
      default: false,
    },
    instruction: {
      type: Object,
    },
  },
  methods: {
    removeStep(id) {
      this.$emit('removeStep', id)
    },
  },
}
</script>

<style scoped>
.instruction__item {
  font-size: 16px;
  border-bottom: 1px solid #f0f2eb;
  counter-increment: instruction;
  line-height: 1.6;
  margin-bottom: 1.25rem;
  padding-bottom: 1.25rem;
}

.instruction__item-image {
  max-width: 300px !important;
}

.instruction__item:before {
  content: counter(instruction) '.';
  font-weight: 700;
  font-size: 23px;
  margin-right: 8px;
  float: left;
  line-height: 1;
}

.column {
  padding-top: 0rem !important;
}
</style>
