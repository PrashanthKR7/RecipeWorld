<template>
  <div class="columns is-centered">
    <div class="column is-half">
      <b-field>
        <div class="select control">
          <select>
            <option :key="category.id" v-for="category in categories">{{category.name}}</option>
          </select>
        </div>
        <b-input icon="magnify" v-model="searchTerm" class="is-expanded" type="search" placeholder="Search..."></b-input>
        <p class="control">
          <button class="button is-primary" @click="search">Search</button>
        </p>
      </b-field>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Search',
  data() {
    return {
      searchTerm: '',
      filters: [],
      categories: [
        {
          id: 0,
          name: 'All',
        },
        {
          id: 1,
          name: 'Indian',
        },
      ],
    }
  },
  methods: {
    search() {
      console.log(this.searchTerm)
    },
  },
}
</script>

<style>
input.input {
  min-width: 100px;
}

.select select {
  border-radius: 4px 0px 0px 4px;
  z-index: 1;
}
</style>
