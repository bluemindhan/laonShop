import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import ViteRequireContext from '@originjs/vite-plugin-require-context'
const path = require("path");

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
    },
  },
  plugins: [vue(), ViteRequireContext()],
  build: {
    outDir: "../server/src/public",
  },
});
