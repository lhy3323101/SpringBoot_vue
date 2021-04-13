export const BaseUrl = () => {
  return process.env.NODE_ENV === 'development' ? 'http://localhost:8082/' : 'http://114.55.171.17:8082/';
}

