import { Rule, SchematicContext, Tree } from '@angular-devkit/schematics';
import { Schema as HelloSfeirOptions} from './schema'


// You don't have to export the function as default. You can also have more than one rule factory
// per file.
export default function (options: HelloSfeirOptions): Rule {
  return (tree: Tree, _context: SchematicContext) => {
    tree.create("hello-sfeir.html", `<h1>Hello Sfeir, from ${options.location} 👋</h1>`);
    return tree;
  };
}
