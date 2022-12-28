export function requireAuth() {
  return (to, from, next) => {
    next();
  }
}
