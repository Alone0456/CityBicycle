<template>
  <div style="padding: 0 15px;" @click="toggleClick">
    <!-- <svg :class="{ 'is-active': isActive }" class="hamburger" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"
      width="64" height="64">
      <path
        d="M408 442h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8zm-8 204c0 4.4 3.6 8 8 8h480c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8H408c-4.4 0-8 3.6-8 8v56zm504-486H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zm0 632H120c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8zM142.4 642.1L298.7 519a8.84 8.84 0 0 0 0-13.9L142.4 381.9c-5.8-4.6-14.4-.5-14.4 6.9v246.3a8.9 8.9 0 0 0 14.4 7z" />
    </svg> -->

    <svg style="margin-top: 15px;" :class="{ 'is-active': isActive }" t="1710420556629" class="icon"
      viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5113" width="20" height="20">
      <path
        d="M890.2 115H133.8c-20.9 0-37.8 16.9-37.8 37.8v37.8c0 20.9 16.9 37.8 37.8 37.8h756.4c20.9 0 37.8-16.9 37.8-37.8v-37.8c0-20.9-16.9-37.8-37.8-37.8zM890.2 455.3H398.5c-20.9 0-37.8 16.9-37.8 37.8v37.8c0 20.9 16.9 37.8 37.8 37.8h491.6c20.9 0 37.8-16.9 37.8-37.8v-37.8c0.1-20.9-16.8-37.8-37.7-37.8zM890.2 795.6H133.8c-20.9 0-37.8 16.9-37.8 37.8v37.8c0 20.9 16.9 37.8 37.8 37.8h756.4c20.9 0 37.8-16.9 37.8-37.8v-37.8c0-20.9-16.9-37.8-37.8-37.8zM266.2 600.7V423.3c0-22-29.2-33.1-46.2-17.5l-97.1 88.7c-10.6 9.7-10.6 25.3 0 35l97.1 88.7c17 15.5 46.2 4.5 46.2-17.5z"
        fill="#97a8be" p-id="5114"></path>
    </svg>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',
  props: {
    isActive: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    toggleClick() {
      this.$emit('toggleClick')
    }
  }
}
</script>

<style scoped>
.hamburger {
  display: inline-block;
  vertical-align: middle;
  width: 20px;
  height: 20px;
}

.hamburger.is-active {
  transform: rotate(180deg);
}
</style>
