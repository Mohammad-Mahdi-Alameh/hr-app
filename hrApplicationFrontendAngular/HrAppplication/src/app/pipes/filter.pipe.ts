import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(value: any[], filterString: string, propNames: string[]): any[] {
    if (!value || value.length === 0 || !filterString || !propNames || propNames.length === 0) {
      return value;
    }
    const filterStringLower = filterString.toLowerCase();
    return value.filter(item => {
      for (const prop of propNames) {
        if (item.hasOwnProperty(prop)) {
          let propValue = item[prop];
          if (typeof propValue !== 'string') {
            propValue = propValue.toString();
          }
          propValue = propValue.toLowerCase();
          if (propValue.includes(filterStringLower)) {
            return true;
          }
        }
      }
      return false;
    });
  }



}
