export class Mission {
  id: number | undefined;
  wording: string | undefined;
  actions: undefined;

  constructor(data?: any) {
    if (data != null) {
      this.id = data.id;
      this.wording = data.wording;
      this.actions = data.actions;
      if(data.actionsById != null) {
         this.actions = data.actionsById;
      }
    }
  }
}
