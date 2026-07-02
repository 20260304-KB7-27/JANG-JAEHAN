import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(), vueDevTools()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },

  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
      },
    },
  },

  build: {
    // 빌드 후 저장될 디렉토리 위치
    outDir:
      'D:/KB_Workspace/JANG-JAEHAN/09_Spring+Vue/backend/src/main/webapp/resources',
    // 지정한 경로에 빌드 파일들이 생성될 때 기존 것들을 다 지우고 싶으면 true
    emptyOutDir: true,
  },
});
