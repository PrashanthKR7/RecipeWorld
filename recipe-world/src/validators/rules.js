const rules = {
  ingredient: {
    getMessage: (field, args, data) =>
      (data && data.message) || 'Something went wrong',
    validate: (value, args) =>
      new Promise(resolve => {
        resolve({
          valid: !(
            args.includes(value.name) ||
            value.name.trim().length === 0 ||
            value.quantity.trim().length === 0
          ),
          data: (function() {
            if (value.name.trim().length === 0) {
              return { message: 'Ingredient name is not a valid value' }
            } else if (value.quantity.trim().length === 0) {
              return { message: 'Quantity is not a valid value' }
            } else if (args.includes(value.name)) {
              return {
                message: `'${
                  value.name
                }' already exists in the ingredient list.`,
              }
            }
          })(),
        })
      }),
  },
  step: {
    getMessage: () => 'The procedural step cannot be empty.',
    validate: value => value.content.trim().length > 0,
  },
}

export default Validator => {
  Object.keys(rules).forEach(rule => {
    Validator.extend(rule, rules[rule])
  })
}
