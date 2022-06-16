export class UserInscription {
  id: number | undefined;
  mission: string | undefined;
  date: string | undefined;
  actions: undefined;

  constructor(data?: any) {
    if (data != null) {
      this.id = data.id;
      this.date = data.date;
      if(data.missionByFkMission != null) {
        this.mission = data.missionByFkMission.wording;
        this.actions = data.missionByFkMission.actionsById;
      }
    }
  }
}
